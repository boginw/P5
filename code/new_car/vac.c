#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <limits.h>

#include "ev3.h"
#include "ev3_port.h"
#include "ev3_tacho.h"

#define _sleep(ms) usleep(ms * 1000)
#define PORT 9090

uint8_t motors[] = {0, 0};

/**
 * Initializes EV3 and the motors connected
 * @return Success status
 */
int initMotors() {

    // init ev3dev-c code
    if (ev3_init() == -1) {
        return 0;
    }

    // wait for motors to be connected and init them
    while (ev3_tacho_init() != 2) {
        _sleep(1000);
    }

    return ev3_search_tacho(LEGO_EV3_L_MOTOR, &motors[0], 0) &&
           ev3_search_tacho(LEGO_EV3_L_MOTOR, &motors[1], (uint8_t) (motors[0] + 1));
}

/**
 * uninit all connected motors
 * @returns success status
 */
int uninitMotors() {
    multi_set_tacho_command_inx(motors, TACHO_STOP);
    ev3_uninit();
    return 1;
}

/**
 * Makes a motor drive
 * @param sn The motor to drive
 */
void drive(double percentage) {
    int max_speed = INT_MAX, speed, local_max;
    uint8_t i;

    // find the minimum maximum speed of all motors
    for (i = 0; i < sizeof(motors) / sizeof(uint8_t); i++) {
        get_tacho_max_speed(motors[i], &local_max);

        if (local_max < max_speed) {
            max_speed = local_max;
        }
    }

    // set the current speed as a fraction of max speed
    speed = (int) (max_speed * percentage);
    multi_set_tacho_speed_sp(motors, speed);
    multi_set_tacho_stop_action_inx(motors, TACHO_COAST);
    multi_set_tacho_command_inx(motors, TACHO_RUN_FOREVER);
}


/**
 * Close the program and motors
 * @param signo Signal number
 */
void onExit(int signo) {
    if (signo == SIGINT) {
        uninitMotors();
        puts("Motors uninitialized");
        exit(0);
    }

    exit(0);
}

int main(void) {
    char buffer[8] = {0};
    struct sockaddr_in address;
    int server_fd, new_socket, msg, opt = 1, addrlen = sizeof(address);
    double speed;

    if (signal(SIGINT, onExit) == SIG_ERR) {
        perror("\ncan't catch SIGINT\n");
        exit(EXIT_FAILURE);
    }

    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }

    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    if (!initMotors()) {
        perror("motors");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port
    if (bind(server_fd, (struct sockaddr *) &address, sizeof(address)) < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    if (listen(server_fd, 3) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    while (1) {
        if ((new_socket = accept(server_fd, (struct sockaddr *) &address, (socklen_t *) &addrlen)) < 0) {
            perror("accept");
            exit(EXIT_FAILURE);
        }

        read(new_socket, buffer, 8);
        msg = atoi(buffer); // NOLINT(cert-err34-c)
        speed = msg / 80.0;

        drive(speed);
        close(new_socket);
    }
}

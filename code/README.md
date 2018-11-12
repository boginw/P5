# SW502E18 TSR-VAC 
Traffic Sign Recognizing Velocity Adjusting Car

## Install
A special install task has been added to Gradle. To Install leJOS, please run the following:

```bash
gradle install
```

Let it run it's course and follow any on-screen instructions.

## Build 
You need to:
* Install gradle and have it in your path.
* Update `gradle.properties` to reflect your system.

Afterwards, you need to run:

```bash
gradle clean build
```

By executing this command you build and test your the application. If everything runs successfully then you find the application JAR in the subfolder `build/libs`. You can deploy this JAR on the robot.

## Deploy 
You can deploy your application on a Lego Mindstorm EV3 robot running Lejos by executing the following command:

```bash
gradle clean deployEV3
```

If your robot is not connected via USB, but via WiFi then you must update the `gradle.properties` file to point to the correct IP and use the correct username/password.

## Notes

If you, like me, encounter a weird SSH error on deployment, it is probably due to a legacy error with OpenSSH. To test if this is the case, plug the EV3 to to your computer, and run `ssh root@10.0.1.1`. If it asks for the password, then something else is wrong, otherwise if it gives you an error containing the text `diffie-hellman-group1-sha1`, then you're in luck.


The way I solved it, was to edit my `~/.ssh/config` file, and adding the following:

```bash
Host 10.0.1.1
        KexAlgorithms +diffie-hellman-group1-sha1
        Ciphers +aes256-cbc
```

**note:** the `10.0.1.1` is the IP of the EV3
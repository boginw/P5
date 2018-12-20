# Appendix

## Appendix 1 - Gearing test results
\label{sec:appendix_gearing_test}
Note: All calculations include all decimals, while the presented results have been rounded.

| Frames elapsed | m/s  | km/h | Percentage difference [%] |
|----------------|------|------|---------------------------|
| 1583           | 0.61 | 2.18 |                     -0.34 |
| 1595           | 0.60 | 2.17 |                      0.46 |
| 1595           | 0.60 | 2.17 |                      0.42 |
| 1583           | 0.61 | 2.18 |                     -0.29 |
| 1584           | 0.61 | 2.18 |                     -0.25 |
: Results with gear ratio 1:1 {#tbl:ratio11}

| Frames elapsed | m/s  | km/h | Percentage difference [%] |
|----------------|------|------|---------------------------|
| 817           | 1.18 | 4.23 |                      0.60 |
| 812           | 1.18 | 4.26 |                      0.03 |
| 803           | 1.20 | 4.31 |                     -1.14 |
| 816           | 1.18 | 4.24 |                      0.52 |
: Results with gear ratio 1:2 {#tbl:ratio12}

|  Frames elapsed | m/s | km/h | Percentage difference [%] |
|  ------: | ------: | ------: | ------ |
|  835 | 1.15 | 4.14 | 2.91% |
|  802 | 1.20 | 4.31 | -1.16% |
|  816 | 1.18 | 4.24 | 0.63% |
|  792 | 1.21 | 4.36 | -2.38% |
|  826 | 1.16 | 4.19 | 1.78% |
: Results with gear ratio 1:3 {#tbl:ratio13}

|  Frames elapsed | m/s | km/h | Percentage difference [%] |
|  ------: | ------: | ------: | ------ |
|  1035 | 1.39 | 5.01 | 0.61% |
|  2058 | 1.41 | 5.07 | -0.55% |
|  3086 | 1.40 | 5.04 | -0.06% |
: Results with gear ratio 1:4 {#tbl:ratio14}

|  Frames elapsed | m/s | km/h | Percentage difference [%] |
|  ------: | ------: | ------: | ------ |
|  590 | 1.63 | 5.85 | 0.43% |
|  576 | 1.67 | 6.00 | -2.06% |
|  586 | 1.64 | 5.90 | -0.39% |
|  600 | 1.60 | 5.76 | 2.02% |
|  595 | 1.61 | 5.81 | 1.23% |
: Results with gear ratio 1:5 {#tbl:ratio15}

|  Frames elapsed | m/s | km/h | Percentage difference [%] |
|  ------ | ------ | ------ | ------ |
|  701 | 1.37 | 4.93 | -2.68% |
|  706 | 1.36 | 4.90 | -1.98% |
|  744 | 1.29 | 4.65 | 3.28% |
|  730 | 1.32 | 4.74 | 1.37% |
|  715 | 1.34 | 4.83 | -0.61% |
: Results with gear ratio 1:6 {#tbl:ratio16}

## Appendix 2 {#sec:appendix-mem}

Table: The `drive` function

| Type    | Arr | Name                            | size | Scope |
|---------|-----|---------------------------------|------|-------|
| int     |     | max_speed                       |    4 |     0 |
| int     |     | speed                           |    4 |     0 |
| int     |     | local_max                       |    4 |     0 |
| uint8_t |     | i                               |    1 |     0 |
| call    |     | get_tacho_max_speed             |    9 |     1 |
| call    |     | get_tacho_max_speed             |    9 |     1 |
| call    |     | multi_set_tacho_speed_sp        |   12 |     0 |
| call    |     | multi_set_tacho_stop_action_inx |    9 |     0 |
| call    |     | multi_set_tacho_command_inx     |    9 |     0 |
| MAX     |     |                                 |   25 |       |

Table: The `initMotors` function

| Type | Arr | Name             | size | Scope |
|------|-----|------------------|------|-------|
| call |     | ev3_init         |    4 |     0 |
| call |     | ev3_tacho_init   |    4 |     0 |
| call |     | ev3_search_tacho |   13 |     0 |
| call |     | ev3_search_tacho |    4 |     0 |
| MAX  |     |                  |   13 |       |                

Table: The `main` function

| Type               | Arr | Name                     | size | Scope |
|--------------------|----:|--------------------------|-----:|------:|
| int                |     | server_fd                |    4 |     0 |
| int                |     | new_socket               |    4 |     0 |
| int                |     | msg                      |    4 |     0 |
| int                |     | opt                      |    4 |     0 |
| int                |     | addrlen                  |    4 |     0 |
| struct sockaddr_in |     | address                  |    8 |     0 |
| double             |     | speed                    |    8 |     0 |
| char               |   8 | buffer                   |    8 |     0 |
| call               |     | signal                   |   12 |     0 |
| char               |  21 | "\\ncan't catch SIGINT\\n" |   21 |     1 |
| call               |     | socket                   |   12 |     0 |
| char               |  14 | "socket failed"          |   14 |     1 |
| call               |     | setsockopt               |   24 |     0 |
| char               |  11 | "setsockopt"             |   11 |     1 |
| call               |     | initMotors               |   17 |     0 |
| call               |     | bind                     |   16 |     0 |
| char               |  12 | "bind failed"            |   12 |     1 |
| call               |     | listen                   |   12 |     0 |
| char               |   7 | "listen"                 |    7 |     1 |
| call               |     | accept                   |   16 |     1 |
| char               |   6 | "accept"                 |    6 |     2 |
| call               |     | read                     |   16 |     1 |
| call               |     | drive                    |   37 |     1 |
| call               |     | close                    |    8 |     1 |
| MAX                |     |                          |   81 |       |

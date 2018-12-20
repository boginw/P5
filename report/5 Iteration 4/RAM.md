# Memory Analysis

When designing an embedded system, memory analysis is a critical step, as, without it, a fatal error cannot be guaranteed not to occur, because a collision between stack and heap cannot be guaranteed not to occur. In this section, an analysis of the car system's memory usage will be described, along with its troubles and eventual solutions as an effort of trying to prevent fatal memory related errors.

## Change of Operating System

Contrary to C and C++, Java has a built-in GC (garbage collector), which brings many advantages including freeing the user of thinking of when to free allocated objects, as this is handled automatically [@noauthor_java_nodate]. Such advantages are significant in systems where memory is plenty, but in an embedded system it can be hard to assess memory usage. The solution to this might be to disable the GC, but as far as the Oracle Java HotSpot VM options go, this is not possible [@noauthor_java_nodate-1]. OpenJDK seems to have a so-called, No-Op GC that does not do anything [@noauthor_jep_nodate], and as such can be thought of as a disabled GC. Even if one disables the GC, there is no guaranteed way of deallocating objects other than to run GC. Deallocating can be done by manually running the GC at select places during execution using `System.gc()`, which should run the GC on-demand, is merely suggests to the GC to run [@noauthor_system_nodate]. The problem for embedded systems running Java is that without abilities of deallocating objects or control of when the GC is run, it is tough to figure out how much memory is indeed in use, and how much is garbage.

These arguments lead to a switch to another operating system which supported non-garbage-collected languages, such as C or C++. This operating system was EV3DEV [@noauthor_ev3dev_nodate] which supports a large variety of programming languages, including C and C++, which makes it easier to assess the stack and heap.

## EV3DEV

As EV3DEV handles controlling of the motors in a more UNIX manner [@noauthor_everything_nodate], controlling the motors from a user's perspective is as simple as writing the correct value to the correct file [@noauthor_motors_nodate]. Each motor gets its folder in  `/sys/class/tacho-motor/` when connected, which contains files that can be written to in order to control the given motor. To make a motor rotate, write `run-forever` to echo `command`. 

By using the EV3DEV-C library [@kravtsov_lego_2018] controlling the motors (writing to the correct files) becomes simple functions available in C as seen on [@tbl:mem-bash]. By recursively looking at all the subset of EV3DEV-C functions used in the project, the group concluded that the library performed no dynamic allocation, and as such, in the analysis of the project, all library calls to EV3DEV-C are assumed to have a constant cost.

Table: EV3DEV-C functions and the bash equivelant to each function {#tbl:mem-bash}

| C command                   | Bash equivelant                                        |
|-----------------------------|--------------------------------------------------------|
| `ev3_search_tacho`          | `ls /sys/class/tacho-motor`                            |
| `get_tacho_max_speed`       | `cat /sys/class/tacho-motor/motor$1/max_speed`         |
| `set_tacho_speed_sp`        | `echo $1 > /sys/class/tacho-motor/motor$2/speed_sb`    |
| `set_tacho_stop_action_inx` | `echo $1 > /sys/class/tacho-motor/motor$2/stop_action` |
| `set_tacho_command_inx`     | `echo $1 > /sys/class/tacho-motor/motor$2/command`     |

## Methodology

By stepping through the car-system code, and for each branching opportunity, choose the branch with most memory usage, the worst-case memory usage for the car-system can be decided by each scope encountered accumulate the required memory for that scope.

### Assumptions

The following assumptions are made in order to conclude without stepping through the entire operating system.

* Creation of scopes, also known as blocks, such as those required for if-, while- and for-statements, is cost-free.
* Function calls cost the sum of the size of each parameter's type as seen in the function signature and a return address.
* Syscalls are free, except `malloc`, `calloc`, `realloc`, and so on.
* When evaluating function-calls in a scope, only most costly function in the given scope will be counted for, as function-calls are temporary and can be destroyed as soon as the given function returns.

## Results

As can be seen in [@sec:appendix-mem] the theoretical maximum memory used is 81 bytes. The 81 bytes used are not to be seen as the only 81 bytes required to run the program, but rather, the theoretical maximum of memory needed given all external methods do nothing. As the group had limited time to perform these experiments, a more in-depth analysis of was not performed.



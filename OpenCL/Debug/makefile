################################################################################
# Automatically-generated file. Do not edit!
################################################################################

-include ../makefile.init

RM := rm -rf

# All of the sources participating in the build are defined here
-include sources.mk
-include src/subdir.mk
-include subdir.mk
-include objects.mk

ifneq ($(MAKECMDGOALS),clean)
ifneq ($(strip $(C_DEPS)),)
-include $(C_DEPS)
endif
endif

-include ../makefile.defs

# Add inputs and outputs from these tool invocations to the build variables 

# All Target
all: OpenCL

dependents:
	-cd /home/fidgetsinner/workspace/OpenCL/Release && $(MAKE) clean all

# Tool invocations
OpenCL: $(OBJS) $(USER_OBJS) /home/fidgetsinner/workspace/OpenCL/Release/OpenCL
	@echo 'Building target: $@'
	@echo 'Invoking: GCC C Linker'
	gcc -L/usr/X11/lib -L/usr/include/CL -o "OpenCL" $(OBJS) $(USER_OBJS) $(LIBS)
	@echo 'Finished building target: $@'
	@echo ' '

# Other Targets
clean:
	-$(RM) $(EXECUTABLES)$(OBJS)$(C_DEPS) OpenCL
	-@echo ' '

.PHONY: all clean dependents
/home/fidgetsinner/workspace/OpenCL/Release/OpenCL:

-include ../makefile.targets

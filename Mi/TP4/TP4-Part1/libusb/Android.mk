LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := libusb

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILEs := \
		core.c\
		io.c\
		descriptor.c\
		sync.c\
		os/linux_usbfs.c

LOCAL_C_INCLUDES += \
		TP4/libusb/os\


include $(BUILD_SHARED_LIBRARY)

LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE:=libusb_lib
LOCAL_MODULE_TAGS:=optional
LOCAL_SRC_FILES:=core.c descriptor.c io.c sync.c os/linux_usbfs.c
LOCAL_C_INCLUDES+=libusb-1.0.3/libusb/os libusb-1.0.3/libusb/
include $(BUILD_SHARED_LIBRARY)

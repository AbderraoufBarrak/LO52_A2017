LOCAL_PATH := $(call my-dir)
include  $(CLEAR_VARS)

LOCAL_MODULE := libusb
LOCAL_MODULE_TAGS := optional
LOCAl_SRC_FILES := core.c descriptor.c io.c sync.c os/linux_usbf.c
LOCAL_C_INCLUDES += libusb/os

include $(BUILD_SHARED_LIBRARY)

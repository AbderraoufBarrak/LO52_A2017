LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := libusb
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := \
	core.c \
	descriptor.c \
	io.c \
	sync.c \
	darwind_usb.c \
	linux_usbfs.c

LOCAL_C_INCLUDES += \
	libusb \
	libusb/os \

include $(BUILD_SHARED_LIBRARY)

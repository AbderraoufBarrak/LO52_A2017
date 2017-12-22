LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL SRC FILES:= core.c \
		  descriptor.c \
		  io.c \ 
		  sync.
		  os/darwin_usb.c \
		  os/linux_usbfs.c
		  
LOCAL_C_INCLUDES += device/utbm/lo52/libusb-1.0.3/libusb \
		    device/utbm/lo52/libusb-1.0.3/libusb/os
		  
LOCAL MODULE := libusb
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)

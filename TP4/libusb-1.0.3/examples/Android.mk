LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= lsusb.call

LOCAL_MODULE:= lsusb

include $(BUILD_HOST_STATIC_LIBRARY)
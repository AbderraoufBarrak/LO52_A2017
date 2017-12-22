LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= lsusb.c
LOCAL_MODULE := lsusb
LOCAL_C_INCLUDES += $(LOCAL_PATH)/libusb-1.0.3/
LOCAL_SHARED_LIBRARIES := libc libusb_lib
include $(BUILD_EXECUTABLE)

$(call inherit-product, device/linaro/hikey/hikey.mk)
PRODUCT_PACKAGE+= ../libusb
DEVICE_PACKAGE_OVERLAYS:= device/utbm/lo52/overlay
PRODUCT_PROPERTY_OVERRIDES+= \
	ro.hw = lo52 \
	net.dns1 = 8.8.8.8 \
	net.dns2 = 8.8.4.4

PRODUCT_NAME:= lo52
PRODUCT_DEVICE:= lo52
PRODUCT_BRAND:= Patate
PRODUCT_MODEL:= Chaude
include $(call all-subdir-makefiles)
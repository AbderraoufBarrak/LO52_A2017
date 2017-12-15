$(call inherit-product, device/linaro/hikey/hikey.mk)
PRODUCT_PACKAGES +=\
		libusb\
		lssub
DEVICE_PACKAGE_OVERLAYS := device/utbm/lo52/overlay
PRODUCT_PROPERTY_OVERRIDES +=\
			ro.hw = lo52\
			net.dns1 = 8.8.8.8\
			net.dns2 = 4.4.4.4
PRODUCT_NAME := lo52
PRODUCT_DEVICE := lo52
PRODUCT_BRAND := Android
PRODUCT_MODEL := LO52UTBM

include $(call all-subdir-makefiles)

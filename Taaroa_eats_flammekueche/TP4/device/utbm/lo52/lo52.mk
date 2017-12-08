$(call inherit-product,device/linaro/hikey/refs/heads/master/./hikey.mk)

PRODUCT_PACKAGES += libusb
PRODUCT_NAME := lo52
PRODUCT_DEVICE := lo52
PRODUCT_BRAND := Android
PRODUCT_MODEL := lo52_utbm

#personnalisation
DEVICE_PACKAGE_OVERLAYS := device/utbm/lo52/overlay
PRODUCT_PORPERTY_OVERRIDES += ro.hw=lo52 net.dns1=8.8.8.8 net.dns2=4.4.4.4
PRODUCT_PACKAGES += libusb

include $(call all-subdir-makefiles)

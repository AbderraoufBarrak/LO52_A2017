$(call inherit-product, device/hikey/hikey/device-hikey.mk)
PRODUCT_PACKAGES += libusb

DEVICE_PACKAGE_OVERLAYS := device/utbm/lo52/overlay

PRODUCT_PROPERTY_OVERRIDES += ro.hw = lo52 \
			      net dns1 = 8.8.8.8 \
			      net dns2 = 4.4.4.4

PRODUCT_NAME := lo52
PRODUCT_MODEL := BienCache
PRODUCT_BRAND := ElleEstOuLaPoulette
PRODUCT_DEVICE := Poulette2.0

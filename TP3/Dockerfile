FROM ubuntu

RUN apt-get update & \
    apt-get install build-essential python bc -y

COPY aarch64-linux-android-4.9 /opt/aarch64-linux-android-4.9

ENV ARCH arm64
ENV CROSS_COMPILE /opt/aarch64-linux-android-4.9/bin/aarch64-linux-android-

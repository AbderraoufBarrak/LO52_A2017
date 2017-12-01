FROM ubuntu

RUN apt-get update && \
    apt-get install build-essential python bc libncurses5-dev -y

COPY aarch64-linux-android-4.9 /opt/aarch64-linux-android-4.9

ENV ARCH arm64
ENV CROSS_COMPILE /opt/aarch64-linux-android-4.9/bin/aarch64-linux-android-

RUN mkdir /mnt/hikey-linaro
WORKDIR /mnt/hikey-linaro

COPY entrypoint.sh /entrypoint.sh
CMD ["/entrypoint.sh"]

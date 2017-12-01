#!/bin/bash

docker build -t ubuntu-lo52 .
docker run -it -v $PWD/hikey-linaro:/mnt/hikey-linaro -e PUID=$(id -u) -e PGID=$(id -g) ubuntu-lo52

#!/bin/bash

docker build -t ubuntu-lo52 .
docker run -it -v $PWD/hikey-linaro:/mnt/hikey-linaro ubuntu-lo52

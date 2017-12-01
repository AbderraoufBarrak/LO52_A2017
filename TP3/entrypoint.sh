#!/bin/bash

groupadd -fg $PGID star_lord
useradd -u $PUID -g $PGID star_lord
su star_lord

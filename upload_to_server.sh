#!/bin/bash
#
# Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
# You are allowed to use this code under the GPL3 license, which allows
# commercial use, distribution, modification, and licensed works,
# providing that you distribute your code under the same or similar license.
#

# $1 = username@host
# $2 = port
cd target/ || exit
sftp -o PasswordAuthentication=no -P "$2" "$1" <<END
cd plugins
rm MinecraftManhunt*.jar
put MinecraftManhunt*.jar
END
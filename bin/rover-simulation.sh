#!/bin/bash
SCRIPT_PATH=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P)
BASE_PATH=$(dirname ${SCRIPT_PATH})

INPUT_FILE=$(readlink -f $1)

cd ${BASE_PATH}
java -jar target/mia-rover.jar $INPUT_FILE

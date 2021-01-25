#!/bin/bash
SCRIPT_PATH=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P)
BASE_PATH=$(dirname ${SCRIPT_PATH})

cd ${BASE_PATH}
java -jar target/mia-rover.jar

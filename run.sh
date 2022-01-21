#!/bin/bash

(./gradlew clean build publish; cd ./_localtest; ./gradlew build $1)
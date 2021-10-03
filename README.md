# REST API for TODO-items
## Setup
- Trying out VS Code Devcontianer to have more control of the development environment and avoid installing dependencies in host system
- The app is running in a vscode devcontainer. The mongodb is running in a separate container

## Progress
- Created project with vs code java extension template
- Added devcontainer files
- added docker-compose to set up mongodb container and docker network
- Used code from [Spark/Java red-green-counter example](https://github.com/selabhvl/dat250-sparkjava-counter) as a starting point


## Problems
- Had problems with the Java language support when running in devcontainer. Found help [here](https://github.com/redhat-developer/vscode-java/issues/743). Problem seemed to be caused by lombok extension installation. Java extension where trying to open lombok extension but lombok was not available in the devcontainer.
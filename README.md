# layouts
The layouts ReST service calculate print layouts for use in heightmap and shaded relief generation.
## Run the tests
### From command line
`./gradlew test`
### From IntelliJ
Right click src/test directory and click Run
## Deploy
Deployment scripts to Kubernetes are included in the scripts/ directory.
`scripts/deploy`
The deploy script builds and pushes a new Docker image,
creates or updates the Kubernetes service and deployment,
and waits for changes to be visible at the deployment URL.

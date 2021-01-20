# layouts
The layouts ReST service calculate print layouts for use in heightmap and shaded relief generation.
## Run the tests
`./gradlew cleanTest test`
### Against a deployment
`baseUrl=http://painkiller.arctair.com/layouts ./gradlew cleanTest test`
## Deploy
Deployment scripts to Kubernetes are included in the scripts/ directory.
`scripts/deploy`
The deploy script builds and pushes a new Docker image,
creates or updates the Kubernetes service and deployment,
and waits for changes to be visible at the deployment URL.

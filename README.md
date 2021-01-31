# layouts
The layouts ReST service calculate print layouts for use in heightmap and shaded relief generation.
## Run the tests
`./gradlew cleanTest test`
### Against a deployment
`layouts_baseUrl=http://painkiller.arctair.com/layouts ./gradlew cleanTest test`
## Build, deploy, verify
`scripts/ci`
The ci script executes these steps:
1. Build jar file
1. Build and push Docker image
1. Create or update dark (non-production) Kubernetes service and deployment
1. Run blackbox tests against dark deployment baseUrl
1. Swap dark deployment and live deployment

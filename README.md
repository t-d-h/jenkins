# jenkins
---
Pipeline steps:

User commit new package to "https://github.com/t-d-h/python-webserver.git", Github will trigger the pipeline
1, "Build package" Stage
- Git clone https://github.com/t-d-h/python-webserver.git
- Build the code to .deb package using dpkg
- Rename to right format

2, "Deploy to testing node" 
- Copy .deb package to testing node
- Install .deb package and check exit code
- try to curl localhost and check with exit code

3, Approve
- Ask if user want to deploy to Production

4, Deploy on first node
- Change nginx Load balancing to "Only forward traffic to second node"
- Install .deb package and check exit code
- try to curl from nginx Load balancing and check if serivce is running
- Switch traffic to both 2 Production nodes

5, Deploy on second node

- Change nginx Load balancing to "Only forward traffic to first node"
- Install .deb package and check exit code
- try to curl from nginx Load balancing and check if serivce is running
- Switch traffic to both 2 Production nodes


---
That's all, Im going to add "Notify to telegram in future" (or not because of my laziness :( )




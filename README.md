### Things
1. Forward Slice => All statements that could be affected if you change a given statement.Think of it like ripple effects in water:



### Run

```bash
chmod +x run.sh

# Show all dependencies for a file
./run.sh examples/Example1.java

# Analyze impact of line 3
./run.sh examples/Example1.java 3
```

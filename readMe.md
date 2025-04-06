# Maze Generator and Solver

This project generates a random maze of size `n x m` with a guaranteed path from the top-left `(0,0)` to the bottom-right `(n-1,m-1)` using DFS (Depth First Search).  
It also provides functionality to solve the maze using DFS or BFS to find a valid path.

---

## Features

- Random Maze Generation
- Guaranteed Solvable Path
- Maze Solving Algorithms:
  - DFS (Depth First Search)
  - BFS (Breadth First Search) for shortest path
- Configurable Maze Size

---

## How it works

1. The maze is initially filled with walls (`#`).
2. DFS is used to carve out a path from start to end.
3. Remaining cells are randomized as walls or empty spaces.
4. Maze solving algorithms traverse the generated maze to find a valid path.

---

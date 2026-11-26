import java.util.*;

public class SocialNetwork {

    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Set<Integer>> graph = new HashMap<>();

    public void addUser(int id, String name) {

        if (users.containsKey(id)) {
            System.out.println("User ID already exists!");
            return;
        }

        User user = new User(id, name);

        users.put(id, user);
        graph.put(id, new LinkedHashSet<>());

        System.out.println("User added successfully!");
    }

    public void addFriendship(int id1, int id2) {

        if (!users.containsKey(id1) || !users.containsKey(id2)) {
            System.out.println("One or both users do not exist!");
            return;
        }

        if (id1 == id2) {
            System.out.println("A user cannot be friends with themselves!");
            return;
        }

        if (graph.get(id1).contains(id2)) {
            System.out.println("Friendship already exists!");
            return;
        }

        graph.get(id1).add(id2);
        graph.get(id2).add(id1);

        System.out.println("Friendship added successfully!");
    }

    public void removeFriendship(int id1, int id2) {

        if (!users.containsKey(id1) || !users.containsKey(id2)) {
            System.out.println("User not found!");
            return;
        }

        graph.get(id1).remove(id2);
        graph.get(id2).remove(id1);

        System.out.println("Friendship removed!");
    }

    public void displayUsers() {

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("\n--- All Users ---");

        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void displayFriends(int id) {

        if (!users.containsKey(id)) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("\nFriends of " + users.get(id).getName() + ":");

        Set<Integer> friends = graph.get(id);

        if (friends.isEmpty()) {
            System.out.println("No friends yet.");
            return;
        }

        for (int friendId : friends) {
            System.out.println(users.get(friendId));
        }
    }

    public void shortestPath(int source, int destination) {

        if (!users.containsKey(source) || !users.containsKey(destination)) {
            System.out.println("User not found!");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        queue.add(source);
        visited.add(source);
        parent.put(source, -1);

        while (!queue.isEmpty()) {

            int current = queue.poll();

            if (current == destination) {
                break;
            }

            for (int friend : graph.get(current)) {

                if (!visited.contains(friend)) {

                    visited.add(friend);
                    parent.put(friend, current);
                    queue.add(friend);
                }
            }
        }

        if (!visited.contains(destination)) {
            System.out.println("No connection exists between these users.");
            return;
        }

        List<Integer> path = new ArrayList<>();

        int current = destination;

        while (current != -1) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);

        System.out.println("\nShortest connection:");

        for (int i = 0; i < path.size(); i++) {

            System.out.print(users.get(path.get(i)).getName());

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nDegrees of connection: " + (path.size() - 1));
    }

    public void exploreNetworkDFS(int startId) {

        if (!users.containsKey(startId)) {
            System.out.println("User not found!");
            return;
        }

        Set<Integer> visited = new HashSet<>();

        System.out.println("\nDFS Network Traversal:");

        dfs(startId, visited);
        System.out.println();
    }

    private void dfs(int current, Set<Integer> visited) {

        visited.add(current);

        System.out.print(users.get(current).getName() + " ");

        for (int friend : graph.get(current)) {

            if (!visited.contains(friend)) {
                dfs(friend, visited);
            }
        }
    }

    public void mutualFriends(int id1, int id2) {

        if (!users.containsKey(id1) || !users.containsKey(id2)) {
            System.out.println("User not found!");
            return;
        }

        Set<Integer> common = new HashSet<>(graph.get(id1));

        common.retainAll(graph.get(id2));

        System.out.println("\nMutual Friends:");

        if (common.isEmpty()) {
            System.out.println("No mutual friends.");
            return;
        }

        for (int id : common) {
            System.out.println(users.get(id));
        }
    }

    public void suggestFriends(int id) {

        if (!users.containsKey(id)) {
            System.out.println("User not found!");
            return;
        }

        Map<Integer, Integer> mutualCount = new HashMap<>();

        for (int friend : graph.get(id)) {

            for (int candidate : graph.get(friend)) {

                if (candidate != id && !graph.get(id).contains(candidate)) {

                    mutualCount.put(
                        candidate,
                        mutualCount.getOrDefault(candidate, 0) + 1
                    );
                }
            }
        }

        List<Integer> suggestions = new ArrayList<>(mutualCount.keySet());

        suggestions.sort((a, b) ->
            mutualCount.get(b) - mutualCount.get(a)
        );

        System.out.println("\nFriend Suggestions for " + users.get(id).getName() + ":");

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions available.");
            return;
        }

        for (int candidate : suggestions) {

            System.out.println(
                users.get(candidate).getName()
                + " | Mutual Friends: "
                + mutualCount.get(candidate)
            );
        }
    }
}
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();

        while (true) {

            System.out.println("\n===== FRIEND NETWORK =====");
            System.out.println("1. Add User");
            System.out.println("2. Add Friendship");
            System.out.println("3. Remove Friendship");
            System.out.println("4. Display All Users");
            System.out.println("5. Display Friends");
            System.out.println("6. BFS - Shortest Connection");
            System.out.println("7. DFS - Explore Network");
            System.out.println("8. Find Mutual Friends");
            System.out.println("9. Friend Suggestions");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.print("Enter user ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter user name: ");
                    String name = sc.nextLine();

                    network.addUser(id, name);
                    break;

                case 2:
                    System.out.print("Enter first user ID: ");
                    int u1 = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter second user ID: ");
                    int u2 = Integer.parseInt(sc.nextLine());

                    network.addFriendship(u1, u2);
                    break;

                case 3:
                    System.out.print("Enter first user ID: ");
                    int r1 = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter second user ID: ");
                    int r2 = Integer.parseInt(sc.nextLine());

                    network.removeFriendship(r1, r2);
                    break;

                case 4:
                    network.displayUsers();
                    break;

                case 5:
                    System.out.print("Enter user ID: ");
                    int fId = Integer.parseInt(sc.nextLine());

                    network.displayFriends(fId);
                    break;

                case 6:
                    System.out.print("Enter source user ID: ");
                    int source = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter destination user ID: ");
                    int destination = Integer.parseInt(sc.nextLine());

                    network.shortestPath(source, destination);
                    break;

                case 7:
                    System.out.print("Enter starting user ID: ");
                    int start = Integer.parseInt(sc.nextLine());

                    network.exploreNetworkDFS(start);
                    break;

                case 8:
                    System.out.print("Enter first user ID: ");
                    int m1 = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter second user ID: ");
                    int m2 = Integer.parseInt(sc.nextLine());

                    network.mutualFriends(m1, m2);
                    break;

                case 9:
                    System.out.print("Enter user ID: ");
                    int sId = Integer.parseInt(sc.nextLine());

                    network.suggestFriends(sId);
                    break;

                case 0:
                    System.out.println("Exiting Friend Network. Bye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Programming Assignment 3
 * 
 * Question 2: Library Stack
 * JavaFx
 */
public class Library extends Application {

    /**
     * Used to determine what change occured in the stack
     */
    public enum ChangeType {
        PUSH, POP;

        /**
         * The object that was changed
         */
        private List changedObj;

        /**
         * The changed object(s) are packaged as a list
         *
         * @return The list of changed objects
         */
        public List getChangedObj() {
            return changedObj;
        }

        /**
         * Method to accept the changed object
         *
         * @param obj the list of objects that were changed in the stack
         * @return this enum
         */
        public ChangeType setChangedObj(List obj) {
            this.changedObj = obj;
            return this;
        }
    }

    
    public class Book {
        public String title;

        public Book(String titleIn) {
            this.title = titleIn;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    /**
     * A crude implementation of an observable stack It includes the main features
     * of a stack namely: push, pop, peek.
     *
     * @param <T> Any type
     */
    public class ObservableStack<T> extends SimpleListProperty<T> {
        // private final LinkedList<T> stack;
        private final ArrayList<T> stack;

        public ObservableStack() {
            // this.stack = new LinkedList<>();
            this.stack = new ArrayList<>();
            this.set(FXCollections.observableList(this.stack));
        }

        /**
         * Places the item at the top of the stack
         *
         * @param item the item
         * @return the item that was just pushed
         */
        public T push(T item) {
            Collections.reverse(stack);
            // stack.push(item);
            stack.add(item);
            Collections.reverse(stack);

            fireValueChangedEvent(
                    new StackChange(this.get(), ChangeType.PUSH.setChangedObj(Collections.singletonList(item))));
            return item;
        }

        /**
         * @return the item at the top of the stack granted that the stack is not empty
         * @throws NoSuchElementException if the stack is empty
         */
        public T pop() throws NoSuchElementException {
            Collections.reverse(stack);
            // T temp = stack.pop();
            T temp = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1); // removes the popped element
            Collections.reverse(stack);

            fireValueChangedEvent(
                    new StackChange(this.get(), ChangeType.POP.setChangedObj(Collections.singletonList(temp))));
            return temp;
        }

        /**
         * @return the item at the top of the stack granted that the stack is not empty
         * @throws NoSuchElementException if the stack is empty
         */
        public T peek() throws NoSuchElementException {
            Collections.reverse(stack);
            // T temp = stack.pop();
            T temp = stack.get(stack.size() - 1);
            Collections.reverse(stack);

            fireValueChangedEvent(
                    new StackChange(this.get(), ChangeType.POP.setChangedObj(Collections.singletonList(temp))));
            return temp;
        }

        /**
         * Pushes the element to the top of the stack
         *
         * @param element the element to add
         * @return Always returns true
         * @see #push(Object)
         */
        @Override
        public boolean add(T element) {
            push(element);
            return true;
        }

        /**
         * Removes an element at the given index
         *
         * @param i the index to remove from
         * @return The element that was removed
         * @throws IllegalArgumentException if i is not 0. The stack can only access the
         *                                  top element
         * @see #pop()
         */
        @Override
        public T remove(int i) throws IllegalArgumentException {
            if (0 == i) {
                return pop();
            }
            throw new IllegalArgumentException("Can only modify the top of the stack " + i);
        }

        /**
         * Effectively empties the stack given that the stack is not alredy empty
         *
         * @return true if the stack was emptied
         * @throws NoSuchElementException if the stack is already empty
         */
        public boolean removeAll() throws NoSuchElementException {
            this.get().remove(0, getSize());
            return true;
        }

        /**
         * Adds an element to the given index
         *
         * @param i       the index to add the element at
         * @param element the element to add to the stack
         * @throws IllegalArgumentException if the index specified is not 0. Only the
         *                                  top of the stack is accessible
         * @see #push(Object)
         */
        @Override
        public void add(int i, T element) throws IllegalArgumentException {
            if (0 == i) {
                push(element);
            }
            throw new IllegalArgumentException("Can only modify the top of the stack " + i);
        }

        /**
         * Adds the elements from the collection into the stack in the order they are
         * specified
         *
         * @param elements the collection to be added to this stack
         * @return true
         * @throws NullPointerException if the collection is null
         */
        @Override
        public boolean addAll(Collection<? extends T> elements) throws NullPointerException {
            // elements.forEach(stack::push);
            elements.forEach(stack::add);
            fireValueChangedEvent(
                    new StackChange(this.get(), ChangeType.PUSH.setChangedObj(new ArrayList<>(elements))));
            return true;
        }

        /**
         * Adds the contents of the array into the stack
         *
         * @param elements the array of elememts to add
         * @return true
         * @see #addAll(Collection)
         */
        @Override
        public boolean addAll(T... elements) {
            return addAll(Arrays.asList(elements));
        }

        @Override
        public boolean addAll(int i, Collection<? extends T> elements) {
            throw new UnsupportedOperationException();
        }

        /**
         * Attempt to remove an arbitrary object from the stack is not permitted
         *
         * @param obj The object to remove
         * @return Nothing
         * @throws UnsupportedOperationException Removing an arbitrary object is not
         *                                       permitted Use {@link #pop()}
         */
        @Override
        public boolean remove(Object obj) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Operation not allowed, use pop");
        }

        /**
         * Attempt to remove a range of objects from the stack, this is also not
         * permitted
         *
         * @param from Start removing from here
         * @param to   To here
         * @throws UnsupportedOperationException {@link #remove(Object)}
         */
        @Override
        public void remove(int from, int to) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Operation not allowed, use pop");
        }

        @Override
        public boolean removeAll(T... elements) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection<?> objects) {
            throw new UnsupportedOperationException();
        }

        private final class StackChange extends ListChangeListener.Change<T> {

            private final ChangeType type;
            private boolean onChange;

            /**
             * Constructs a new change done to a list.
             *
             * @param list that was changed
             */
            public StackChange(ObservableList<T> list, ChangeType type) {
                super(list);
                this.type = type;
                onChange = false;
            }

            @Override
            public boolean wasAdded() {
                return type == ChangeType.PUSH;
            }

            @Override
            public boolean wasRemoved() {
                return type == ChangeType.POP;
            }

            @Override
            public boolean next() {
                if (onChange) {
                    return false;
                }
                onChange = true;
                return true;
            }

            @Override
            public void reset() {
                onChange = false;
            }

            /**
             * Because this is a stack, all push and pop happen to the first item in the
             * stack
             *
             * @return index of the first item
             */
            @Override
            public int getFrom() {
                if (!onChange) {
                    throw new IllegalStateException(
                            "Invalid Change state: next() must be called before inspecting the Change.");
                }
                return 0;
            }

            /**
             * @return the size of the list returned which indicates the end of the change
             */
            @Override
            public int getTo() {
                if (!onChange) {
                    throw new IllegalStateException(
                            "Invalid Change state: next() must be called before inspecting the Change.");
                }
                return type.getChangedObj().size();
            }

            @Override
            public List<T> getRemoved() {
                return wasRemoved() ? type.getChangedObj() : Collections.emptyList();
            }

            @Override
            protected int[] getPermutation() {
                return new int[0];
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 450, 400, Color.WHITE);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(60, 60, Double.MAX_VALUE);
        ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2, column3);

        Label availableBooksLbl = new Label("Available Books");
        gridpane.add(availableBooksLbl, 0, 0);
        GridPane.setHalignment(availableBooksLbl, HPos.CENTER);

        Label libraryStackLb1 = new Label("Library Stack");
        GridPane.setHalignment(libraryStackLb1, HPos.CENTER);
        gridpane.add(libraryStackLb1, 2, 0);

        final ObservableList<Book> availableBooks = FXCollections.observableArrayList(
            new Book("A Time for Angels"),
            new Book("Brokeback Mountain"), 
            new Book("Gone With the Wind"), 
            new Book("The Kissing Game"), 
            new Book("Unwind"), 
            new Book("Skinjackers"), 
            new Book("Harry Potter"), 
            new Book("Stargirl"), 
            new Book("Naruto"), 
            new Book("Scythe"));
        final ListView<Book> availableBooksListView = new ListView<>(availableBooks);
        gridpane.add(availableBooksListView, 0, 1);

        final ObservableStack<Book> libraryStack = new ObservableStack<Book>();
        final ListView<Book> libraryStackListView = new ListView<>(libraryStack);
        libraryStackListView.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });
        gridpane.add(libraryStackListView, 2, 1);

        Button pushButton = new Button(" Push ");
        pushButton.setOnAction((ActionEvent event) -> {
            Book selectedOnStack = libraryStackListView.getSelectionModel().getSelectedItem();
            if (selectedOnStack != null) {
                libraryStackListView.getSelectionModel().clearSelection();
            }

            Book selectedBook = availableBooksListView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                availableBooksListView.getSelectionModel().clearSelection();
                availableBooks.remove(selectedBook);
                libraryStack.push(selectedBook);
            }
        });

        Button popButton = new Button(" Pop  ");
        popButton.setOnAction((ActionEvent event) -> {
            Book selectedOnStack = libraryStackListView.getSelectionModel().getSelectedItem();
            if (selectedOnStack != null) {
                libraryStackListView.getSelectionModel().clearSelection();
            }

            Book s = libraryStack.pop();
            availableBooks.add(s);
        });

        Button peekButton = new Button(" Peek ");
        peekButton.setOnAction((ActionEvent event) -> {
            Book s = libraryStack.peek();
            libraryStackListView.getSelectionModel().selectFirst();
        });
        VBox vbox = new VBox(5);
        vbox.getChildren().addAll(pushButton, popButton, peekButton);

        gridpane.add(vbox, 1, 1);
        root.setCenter(gridpane);

        GridPane.setVgrow(root, Priority.ALWAYS);
        primaryStage.setTitle("Library");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

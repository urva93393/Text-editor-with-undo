 Text Editor with Undo (Java)

A menu-driven console text editor built in **Java** for the **Data Structures Lab (CS216L)** project.
It uses a **Singly Linked List** to store text lines and a **Stack** (linked nodes) to power the **Undo** feature.

**Institution:** Fazaia Bilquis College of Education for Women, Nur Khan, Rawalpindi
**Submitted To:** Ms Rabiya

Team Members

| Name | Roll No. |
| --- | --- |
| Alishba Noor | 59 |
| Urva | 32 |
| Kubra Batool | 26 |
| Noorulain Ibrar | 5 |
| Warda Shafiq | 67 |

 Features

- Add a line
- Display all lines (with line numbers)
- Delete a line by position
- Search text (case-insensitive linear search)
- Sort lines alphabetically (bubble sort)
- Undo last action
- Show last undo action (peek)
- Document statistics (total lines, undo count)
- Clear document (can be undone)
- Input validation (menu choice, empty text, line numbers, empty undo stack)

 Data Structures and Algorithms

| Component | Used For |
| --- | --- |
| Singly Linked List | Storing and managing document lines |
| Stack (LIFO) | Saving document snapshots for undo (`push`, `pop`, `peek`) |
| Linear Search | Finding text in the document |
| Bubble Sort | Sorting lines alphabetically |

 Time Complexity (n = number of lines)

| Operation | Complexity |
| --- | --- |
| Insert at end | O(n) |
| Delete by position | O(n) |
| Display | O(n) |
| Linear search | O(n) |
| Bubble sort | O(n²) |
| Stack push / pop / peek / size / isEmpty | O(1) |
| Save undo snapshot | O(n) |
| Restore document | O(n) |

Space:O(n) for the list, O(k) stack entries, up to O(kn) for k snapshots.

 Project Structure

```
CS216L_Project1_GroupName/
├── src/
│   ├── TextEditorWithUndo.java   # Main class (menu)
│   ├── TextLinkedList.java       # Singly linked list + search + bubble sort
│   └── ActionStack.java          # Linked-node stack for undo
├── screenshots/                  # Program output screenshots
├── README.md
└── .gitignore
```

How to Run

Requirements: JDK 8 or higher.

```bash
git clone https://github.com/<your-username>/CS216L_Project1_GroupName.git
cd CS216L_Project1_GroupName/src
javac *.java
java TextEditorWithUndo
```

Or open the `src` folder in Eclipse / IntelliJ IDEA / VS Code and run `TextEditorWithUndo`.

Testing

| # | Operation | Expected Result |
| --- | --- | --- |
| 1 | Add a line | Line is added |
| 2 | Display document | All lines shown with numbers |
| 3 | Delete a valid line | Selected line removed |
| 4 | Search existing text | Matching text displayed |
| 5 | Search missing text | "Not found" message |
| 6 | Sort multiple lines | Lines in alphabetical order |
| 7 | Undo an edit | Previous state restored |
| 8 | Clear document, then undo | Cleared document restored |
| 9 | Invalid menu choice | Error message |
| 10 | Undo with empty stack | "Nothing to undo" message |


 Limitations

- Console-based (no GUI)
- Document is stored only in memory (no file saving)
- Undo history is lost when the program exits
- Bubble sort is slow for very large documents
- Undo stores full snapshots, which uses extra memory

 Future Enhancements

- Save / load documents from files
- Graphical user interface
- Redo functionality (second stack)
- Edit / replace existing lines
- Faster sorting algorithm
- Configurable undo history limit

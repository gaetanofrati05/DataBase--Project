<h1>🏢 Airbnb-Style Relational Database Management System</h1>

<p>
  This is a personal project developed for the <strong>Database Systems Exam</strong>. 
  It consists of a complete relational database system designed to manage short-term accommodations across multiple cities, 
  mirroring the core business logic of platforms like <strong>Airbnb</strong>. 
</p>
<p>
  The system features a robust MySQL backend paired with a modular <strong>Java Application</strong> that acts as the data access layer, 
  leveraging <strong>JDBC (Java Database Connectivity)</strong> to safely execute complex relational queries and transaction-like operations.
</p>

---

<h2>🛠️ Tech Stack & Architecture</h2>

<ul>
  <li><strong>Database Backend:</strong> MySQL (Relational Schema design, Primary/Foreign keys optimization).</li>
  <li><strong>Application Layer:</strong> Java (Object-Oriented logic, Modular structural design).</li>
  <li><strong>Data Access Layer:</strong> JDBC (Native driver connectivity, SQL injection prevention via PreparedStatement).</li>
</ul>

<p>The application follows a clean <strong>three-tier separation of concerns</strong> across its classes:</p>
<ol>
  <li><code>DBConnection.java</code>: Manages the lifecycle of the connection pool with the MySQL server.</li>
  <li><code>Query.java</code>: Encapsulates all the SQL queries, analytical computations, and database interaction methods.</li>
  <li><code>App.java</code>: Provides the main entry point and driver interface to run and test the operations.</li>
</ol>

---

<h2>🧠 Database Interaction & Core Logic</h2>

<p>Based on the codebase analysis, the project handles deep backend operations, divided into distinct functional areas:</p>

<h3>1. Secure Infrastructure & Lifecycle</h3>
<ul>
  <li><strong>Connection Management:</strong> <code>DBConnection</code> encapsulates the driver loading and connection parameters securely, returning active <code>Connection</code> objects and ensuring systematic resource cleanup to prevent memory/connection leaks.</li>
</ul>

<h3>2. Business Operations & CRUD Feature Sets</h3>
<ul>
  <li><strong>Data Insertion & Modification:</strong> Implements robust updates to manipulate database records dynamically (e.g., handling accommodation details, user registrations, and city inventories).</li>
  <li><strong>Parameterized Execution:</strong> Utilizes JDBC binding mechanisms to abstract raw queries, guaranteeing data integrity and safety against unintended manipulations.</li>
</ul>

<h3>3. Advanced Relational Queries & Reporting</h3>
<p>The system executes complex relational algebra operations translated into efficient SQL, designed to pull analytical business intelligence:</p>

<table>
  <thead>
    <tr>
      <th>Query Class Metric</th>
      <th>Database Logic & Relational Operations</th>
      <th>Real-World Business Value</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Multi-Table Joins</strong></td>
      <td>Combines accommodation data, cities, host profiles, and reservations using <code>INNER JOIN</code> constraints.</td>
      <td>Provides a consolidated view of accommodation availability filtered by geographical metadata.</td>
    </tr>
    <tr>
      <td><strong>Data Aggregation</strong></td>
      <td>Utilizes SQL aggregate functions (<code>COUNT</code>, <code>SUM</code>, <code>AVG</code>) coupled with <code>GROUP BY</code> clauses.</td>
      <td>Generates instant metrics such as total revenue per host, average price per city, or reservation frequencies.</td>
    </tr>
    <tr>
      <td><strong>Advanced Filtering</strong></td>
      <td>Implements conditional <code>WHERE</code> and <code>HAVING</code> clauses to screen database states.</td>
      <td>Enables users to search for specific accommodations matching budget, high review scores, or location constraints.</td>
    </tr>
  </tbody>
</table>

---

<h2>⚙️ Codebase Structure Overview</h2>

<p>The core package includes the following production-ready source files:</p>

<ul>
  <li>
    <strong><code>DBConnection.java</code></strong>
    <br />Handles environment connection URLs, credentials, and error routines. It opens communication bridges with the target <code>jdbc:mysql://</code> server schema.
  </li>
  <li>
    <strong><code>Query.java</code></strong>
    <br />The heart of the application's data layer. It contains specialized methods that compile, prepare, and run the SQL instructions, parsing the resulting <code>ResultSet</code> streams into readable terminal outputs.
  </li>
  <li>
    <strong><code>App.java</code></strong>
    <br />The controller file. It instantiates the workflow, triggers sequential database requests, and simulates the user interaction loop with the system.
  </li>
</ul>

---

<h2>🤝 Project Status & Support</h2>
<p>
  This project was designed, implemented, and tested entirely by me as an individual academic work. 
  It demonstrates solid foundations in relational theory, database normalization, and programmatic database access.
</p>
<p>
  If you have any advice regarding schema optimization, indexing strategies, or performance improvements on the JDBC wrapper, 
  <strong>contributions and discussions are highly welcome!</strong> Feel free to fork the repository or open an issue. ⭐
</p>

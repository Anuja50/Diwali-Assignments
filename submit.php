<?php
// Simple demo receiver - do NOT use this for production
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $fname = htmlspecialchars($_POST['fname'] ?? '');
    $lname = htmlspecialchars($_POST['lname'] ?? '');
    $email = htmlspecialchars($_POST['email'] ?? '');
    $userid = htmlspecialchars($_POST['userid'] ?? '');

    // Very simple server-side validation (always validate on server too)
    $errors = [];
    if ($fname === '') $errors[] = 'First name required';
    if ($lname === '') $errors[] = 'Last name required';
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) $errors[] = 'Invalid email';
    if ($userid === '') $errors[] = 'User ID required';

    if (!empty($errors)) {
        echo "<h3>Errors:</h3><ul>";
        foreach ($errors as $e) echo "<li>" . $e . "</li>";
        echo "</ul><a href='form_validation.html'>Go back</a>";
        exit;
    }

    echo "<h2>Form submitted successfully</h2>";
    echo "<p>First name: " . $fname . "</p>";
    echo "<p>Last name: " . $lname . "</p>";
    echo "<p>Email: " . $email . "</p>";
    echo "<p>User ID: " . $userid . "</p>";
    echo "<br><a href='form_validation.html'>Back to form</a>";
    exit;
}
header("Location: form_validation.html");
exit;

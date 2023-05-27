import React from 'react';
import Form from "react-bootstrap/Form";
const FileInput = () => {
    return (
        <Form.Group controlId="formFile" className="mb-3">
            <Form.Label>Default file input example</Form.Label>
            <Form.Control type="file" />
        </Form.Group>
    );
};

export default FileInput;
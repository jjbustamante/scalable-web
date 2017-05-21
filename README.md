# scalable-web
This is a microservice develop as part of an assignment.

## Requirements

Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
- <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
  - The provided data needs to be diff-ed and the results shall be available on a third end point
- <host>/v1/diff/<ID>
  - The results shall provide the following info in JSON format
    - If equal return that
    - If not of equal size just return that
    - If of same size provide insight in where the diffs are, actual diffs are not needed. (So mainly offsets + length in the data)


import cv2
from deepface import DeepFace
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route("/home", methods=["GET"])
def homeRoute():
    return "200 OK", 200 

@app.route("/sendImage", methods=["POST"])
def kirimFoto():
    # Get the data sent in the request body
    data = request.get_json()

    # Extract the image data from the "image" key in the JSON data
    image_data_uri = data.get('image')

    # Extract the base64-encoded image data from the data URI
    _, base64_data = image_data_uri.split(',', 1)

    # Decode the base64 image data to binary
    import base64
    image_binary = base64.b64decode(base64_data)

    # Save the binary image data to a file (you can adjust the file path)
    with open('received_image.jpg', 'wb') as img_file:
        img_file.write(image_binary)

    # Perform face verification (modify this part as needed)
    if verify('PythonAPI/SampleIMG.jpg', 'received_image.jpg'):
        return "200 OK", 200
    else:
        return "201 Error", 201

def verify(img1_path, img2_path):
    result = DeepFace.verify(img1_path, img2_path, enforce_detection = False)
    if result['verified']:
        return True
    else:
        return False

if __name__ == "__main__":
    app.run(debug=True)

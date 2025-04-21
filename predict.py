import sys
import joblib

# Load model and encoders
model = joblib.load("recycling_model.pkl")
item_encoder = joblib.load("item_encoder.pkl")
bin_encoder = joblib.load("bin_encoder.pkl")

if len(sys.argv) < 2:
    print("No item provided")
    sys.exit(1)

item = sys.argv[1].strip().lower()

try:
    # Encode item
    encoded_item = item_encoder.transform([item])
    
    # Predict bin
    predicted_bin_encoded = model.predict([encoded_item])
    predicted_bin = bin_encoder.inverse_transform(predicted_bin_encoded)

    print(predicted_bin[0])
except Exception as e:
    print("null")

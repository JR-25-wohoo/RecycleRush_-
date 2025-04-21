import joblib
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import LabelEncoder

# Sample data for training the model
items = ['banana', 'bottle', 'plastic_bag']
bins = ['Compost', 'Recycle', 'Trash']

# Encoding the items and bins
item_encoder = LabelEncoder()
bin_encoder = LabelEncoder()

item_encoded = item_encoder.fit_transform(items)
bin_encoded = bin_encoder.fit_transform(bins)

# Prepare the data for training (using item_encoded as input and bin_encoded as target)
X = np.array(item_encoded).reshape(-1, 1)
y = bin_encoded

# Train a Logistic Regression model
model = LogisticRegression()
model.fit(X, y)

# Save the model and encoders
joblib.dump(model, 'recycling_model.pkl')
joblib.dump(item_encoder, 'item_encoder.pkl')
joblib.dump(bin_encoder, 'bin_encoder.pkl')

print("Model and encoders saved!")

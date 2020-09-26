from glob import glob
import os
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

file_paths = glob('output/UGATIT*/*.jpg')
print(file_paths)
print(BASE_DIR)
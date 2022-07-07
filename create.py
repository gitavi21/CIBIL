
import shutil
import os
 
# path to source directory
src_dir = "C:\\Users\\aviralsrivastava\\fresh\\www\\"
 
# path to destination directory
dest_dir = 'C:\\Users\\aviralsrivastava\\StudioProjects\\JS2\\app\\src\\main\\assets'
 
# getting all the files in the source directory
files = os.listdir(src_dir)
 
shutil.copytree(src_dir, dest_dir,dirs_exist_ok=True)
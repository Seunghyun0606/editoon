from django.shortcuts import render
from django.http import JsonResponse
from rest_framework.decorators import api_view
import json
import os
import numpy as np
from PIL import Image
import base64
from my_UGATIT import UGATIT
import argparse
from utils import *
import json
import cv2
from django.http import FileResponse
from django.http import HttpResponse
from glob import glob
import io
import base64
import matplotlib.pylab as plt

"""parsing and configuration"""

class Args:
    def __init__(self):
        self.file_name=None
        self.imgs=None
        self.phase = 'test'
        self.light = False
        self.dataset ='selfie2anime'
        self.epoch = 100
        self.iteration = 10000
        self.batch_size = 1
        self.print_freq = 1000
        self.save_freq = 1000
        self.decay_flag=True
        self.decay_epoch=50
        self.lr=0.0001
        self.GP_ld=10
        self.adv_weight=1
        self.cycle_weight=10
        self.identity_weight=10
        self.cam_weight=1000
        self.gan_type='lsgan'
        self.smoothing=True
        self.ch=64
        self.n_res=4
        self.n_dis=6
        self.n_critic=1
        self.sn=True
        self.img_size=256
        self.img_ch=3
        self.augment_flag=True
        self.checkpoint_dir='checkpoint'                            
        self.result_dir='../front/src/assets/output'                         
        self.log_dir='logs'
        self.sample_dir='samples'                           

# Create your views here.
@api_view(['POST'])
def ImgtoAnime(request):    
    test = request.FILES
    files={}
    for idx in range(len(test)):
        file_name = 'img'+str((idx+1))
        img = Image.open(test[file_name])
        # img.show()
        img = img.resize((256, 256))
        # img.show()
        img_np = np.array(img)[:,:,:3] 
        files[str(test[file_name])]=[img_np]
   
    args = Args()
    
    gan = ''
    with tf.Session(config=tf.ConfigProto(allow_soft_placement=True)) as sess:
        gan = UGATIT(sess, args)
        # build graph
        gan.build_model() 
        gan.files = files  # arigs.imgs에 넣은이유가?

        gan.test()
        print(" [*] finished!")

    imgs = gan.ret_imgs
    
    res=[]

    for i, img in enumerate(imgs):
        img = (img+1.)/2 * 255
        im = Image.fromarray(img[0].astype(np.uint8), 'RGB')
        
        output = io.BytesIO()
        im.save(output, format='JPEG')
        hex_data = base64.b64encode(output.getvalue())
        return HttpResponse(hex_data, content_type="image/jpeg")

    #여러개할때
    #    res.append(hex_data) 
    #return HttpResponse(res, content_type="image/jpeg")


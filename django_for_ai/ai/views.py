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
   
    print(files)
    args = Args()
    
    args.imgs = files
    gan = ''
    with tf.Session(config=tf.ConfigProto(allow_soft_placement=True)) as sess:
        gan = UGATIT(sess, args)
        # build graph
        gan.build_model() 
        gan.files = files  # arigs.imgs에 넣은이유가?

        gan.test()
        print(" [*] finished!")

    imgs = gan.ret_imgs
    # print(1, imgs)

    # img_test = Image.fromarray(imgs[0], 'RGB')  # dimension이 4차원인이유가...?
    # img.show()

    ret = {}  # ret?? 어디서쓰임?
    t=[]
    for i, img in enumerate(imgs):
        img = (img+1.)/2 * 255
        ret['img{}'.format(i)] = img[0].tolist()
        img = np.asarray(img[0])
        im = Image.fromarray(img, 'RGB')
        t.append(im)

    
        # print(im)
        # output = io.BytesIO()
        # im.save(output, format='JPEG')
        # hex_data = base64.b64encode(output.getvalue())
        # im.show()

        # 데이터를 주고받으려면 binary형태여야함, 다른 형식을 못찾겠어서 일단 지원하는 base64형태로 바꿔서 보냈고, 날라오는거 확인.
        # 근데 파일이 이상함. 노션 -> 문제점에서 좌측 하단처럼 깨져서 파일이옴.
        # 실제로 im.show를 해보면 이상하게 나옴. 원인파악필요.
        # 일단은 한개파일만 주고받은 경우로 만들었고, 다중파일은 그냥 리스트형태로 만들어서 보내주면됨.

        # response = HttpResponse(hex_data, content_type="image/jpeg")

    # return response
    return HttpResponse(t, content_type="image/jpeg")
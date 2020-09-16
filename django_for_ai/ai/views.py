from django.shortcuts import render
from django.http import JsonResponse
from rest_framework.decorators import api_view
import json
import os

# Create your views here.
@api_view(['POST'])
def ImgtoAnime(request):
    file_name = json.loads(request.body)['img']
    
    BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    path=os.path.join(BASE_DIR,"output","UGATIT_selfie2anime_lsgan_4resblock_6dis_1_1_10_10_1000_sn_smoothing",file_name)
    os.system('python my_main.py --file_name {}'.format(file_name))

    return JsonResponse(path,safe=False)
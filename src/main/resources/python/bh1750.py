import smbus
import time

DEVICEADDR = 0x23 

POWER_DOWN = 0x00 
POWER_ON   = 0x01 
RESET      = 0x07 

CONTINUOUS_LOW_RES_MODE = 0x13
CONTINUOUS_HIGH_RES_MODE_1 = 0x10
CONTINUOUS_HIGH_RES_MODE_2 = 0x11
ONE_TIME_HIGH_RES_MODE_1 = 0x20
ONE_TIME_HIGH_RES_MODE_2 = 0x21
ONE_TIME_LOW_RES_MODE = 0x23

bus = smbus.SMBus(1)

def convertToNumber(data):
  result=(data[1] + (256 * data[0])) / 1.2
  return (result)

def readLight(addr=DEVICEADDR):
  data = bus.read_i2c_block_data(addr,ONE_TIME_HIGH_RES_MODE_1)
  return convertToNumber(data)

def main():
    lightValue=readLight()
    print(format(lightValue,'.2f'))

if __name__=="__main__":
   main()
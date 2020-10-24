/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageclient;

import imagebeans.Image;
import imageioimpl.ImageReaderImpl;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import rmi.RemoteInterface;

/**
 *
 * @author ASUS
 */
public class ImageClient {
        
    public HashMap<String,String[]> requestImageRepositoryWithImages(String host) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(host);
        RemoteInterface stub = (RemoteInterface) registry.lookup("ImageServer");
        HashMap<String,String[]> allImageNames = stub.getRepository();
        return allImageNames;
    }
    
    public Image requestImage(String host, String name)throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(host);
        RemoteInterface stub = (RemoteInterface) registry.lookup("ImageServer");
        Image image = stub.getImageByName(name);
        return image;
    }
    
    public void postImage(String host, Image image) throws RemoteException, NotBoundException, IOException{
        Registry registry = LocateRegistry.getRegistry(host);
        RemoteInterface stub = (RemoteInterface) registry.lookup("ImageServer");        
        stub.postImage(image);
    }
    
}

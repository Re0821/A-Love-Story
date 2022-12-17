package com.nana.helper;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileMapHelper{
    private TiledMap tiledMap;
    
    public TileMapHelper(){

    }

    public OrthogonalTiledMapRenderer setupMap(){
        tiledMap = new TmxMapLoader().load("core/src/com/nana/Resource/maps/tutorial.tmx");
        return new OrthogonalTiledMapRenderer(tiledMap);
    }
    
}
package com.orange.util.texturepack;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.orange.opengl.texture.TextureManager;
import com.orange.util.StreamUtils;
import com.orange.util.texturepack.exception.TexturePackParseException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.res.AssetManager;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class TexturePackLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final AssetManager mAssetManager;
	private final TextureManager mTextureManager;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TexturePackLoader(final AssetManager pAssetManager, final TextureManager pTextureManager) {
		this.mAssetManager = pAssetManager;
		this.mTextureManager = pTextureManager;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public TexturePack loadFromAsset(final String pAssetPath, final String pAssetBasePath) throws TexturePackParseException {
		try {
			return this.load(this.mAssetManager.open(pAssetPath), pAssetBasePath);
		} catch (final IOException e) {
			throw new TexturePackParseException("Could not load " + this.getClass().getSimpleName() + " data from asset: " + pAssetPath, e);
		}
	}

	public TexturePack load(final InputStream pInputStream, final String pAssetBasePath) throws TexturePackParseException {
		try{
			final SAXParserFactory spf = SAXParserFactory.newInstance();
			final SAXParser sp = spf.newSAXParser();

			final XMLReader xr = sp.getXMLReader();
			final TexturePackParser texturePackParser = new TexturePackParser(this.mAssetManager, pAssetBasePath, this.mTextureManager);
			xr.setContentHandler(texturePackParser);

			xr.parse(new InputSource(new BufferedInputStream(pInputStream)));

			return texturePackParser.getTexturePack();
		} catch (final SAXException e) {
			throw new TexturePackParseException(e);
		} catch (final ParserConfigurationException pe) {
			/* Doesn't happen. */
			return null;
		} catch (final IOException e) {
			throw new TexturePackParseException(e);
		} finally {
			StreamUtils.close(pInputStream);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
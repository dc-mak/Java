package uk.ac.cam.dcm41.fjava.tick2star;

import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.net.SocketPermission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;
import java.util.PropertyPermission;

public class SafeObjectInputStream extends ObjectInputStream {

	// TODO: What goes/happens here?
	private SecureClassLoader current = (SecureClassLoader) ClassLoader.getSystemClassLoader();
	private final ProtectionDomain protection;

	public SafeObjectInputStream(InputStream in) throws IOException {
		super(in);

		final SocketPermission sockPermission = new SocketPermission("www.cam.ac.uk:" + 80, "connect");
		final PropertyPermission homeFindPermission = new PropertyPermission("user.home", "read");
		final FilePermission filePermission = new FilePermission(System.getProperty("user.home"), "read");
			
		final PermissionCollection permissionColl = new Permissions();
		permissionColl.add(sockPermission);
		permissionColl.add(homeFindPermission);
		permissionColl.add(filePermission);
		
		// null for CodeSource
		protection = new ProtectionDomain(null, permissionColl);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc)
			throws IOException,	ClassNotFoundException {
		
		try {
			return current.loadClass(desc.getName());
		}
		catch (ClassNotFoundException e) {
			return super.resolveClass(desc);
		}

	}

	public void addClass(final String name, final byte[] defn) {
		current = new SecureClassLoader(current) {
			@Override
			protected Class<?> findClass(String className) throws ClassNotFoundException {
				if (className.equals(name)) {
					Class<?> result = defineClass(name, defn, 0, defn.length, protection);
					return result;
				} else {
					throw new ClassNotFoundException();
				}
			}};
	}

}

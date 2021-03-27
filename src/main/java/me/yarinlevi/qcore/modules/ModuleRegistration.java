package me.yarinlevi.qcore.modules;

import me.yarinlevi.qcore.QCore;
import me.yarinlevi.qcore.api.BaseModule;
import me.yarinlevi.qcore.exceptions.ModuleNotFoundException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.logging.Level;
import java.util.zip.ZipFile;

/**
 * @author Quapi
 */
public class ModuleRegistration {
    private QCore instance;

    private final Map<String, BaseModule> modules = new HashMap<>();
    private final Set<Class<?>> mainClasses = new HashSet<>();

    private File extensionDirectory;


    public void loadAll(QCore instance) {
        this.instance = instance;

        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdirs();
        } else {
            extensionDirectory = new File(instance.getDataFolder() + "/extensions");

            if (extensionDirectory.exists() && extensionDirectory.isDirectory()) {

                File[] files = extensionDirectory.listFiles((dir, name) -> name.endsWith(".jar"));

                for (File jar : files) {
                    try {
                        this.loadFile(jar);
                    } catch (ModuleNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                extensionDirectory.mkdirs();
            }
        }
    }

    public List<BaseModule> getModuleList() {
        return new ArrayList<>(modules.values());
    }

    public void enableAll() {
        for (Class<?> main : mainClasses) {
            this.enableFile(main);
        }
    }

    public void unload(String module) {
        BaseModule loadedModule = modules.get(module);

        loadedModule.onDisable();

        mainClasses.remove(loadedModule.getClass());
        modules.remove(module);
    }

    public void load(String module) {
        Class<?> main;
        try {
            main = this.loadFile(new File(extensionDirectory, module + ".jar"));

            this.enableFile(main);
        } catch (ModuleNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void reload(String module) {
        BaseModule unReloadedModule = modules.get(module);

        unReloadedModule.onDisable();

        mainClasses.remove(unReloadedModule.getClass());
        modules.remove(module);

        Class<?> main;
        try {
            main = this.loadFile(new File(extensionDirectory, module + ".jar"));
            this.enableFile(main);

        } catch (ModuleNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void enableFile(Class<?> mainClass) {
        try {
            Object object = mainClass.newInstance();

            if (object instanceof BaseModule) {
                BaseModule module = (BaseModule) object;
                module.onEnable(instance.getApi());
                modules.put(module.getExtensionName(), module);
                instance.getLogger().log(Level.INFO, module.getExtensionName() + " v" + module.getExtensionVersion() + " enabled!");
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Class<?> loadFile(File jar) throws ModuleNotFoundException {
        String mainClass = null;
        try {
            ZipFile zipFile = new ZipFile(jar);

            InputStream is = zipFile.getInputStream(zipFile.getEntry("extension.yml"));

            YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(is));
            mainClass = config.getString("main");

            ClassLoader l = URLClassLoader.newInstance(new URL[]{jar.toURI().toURL()}, getClass().getClassLoader());

            Class<?> clazz = l.loadClass(mainClass);
            mainClasses.add(clazz);

            return clazz;

        } catch (IOException e) {
            instance.getLogger().severe("Error while loading module file " + jar.getName());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            instance.getLogger().severe("Class not found! Wrong main defined in extension.yml?: " + jar.getName() + " class: " + mainClass);
            e.printStackTrace();
        }
        throw new ModuleNotFoundException("Error while loading module file");
    }
}

/**
 * Copyright (C) 2011, FuseSource Corp.  All rights reserved.
 * http://fusesource.com
 *
 * The software in this package is published under the terms of the
 * CDDL license a copy of which has been included with this distribution
 * in the license.txt file.
 */
package org.fusesource.fabric.fab.osgi.url.internal;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.fusesource.fabric.fab.ModuleRegistry;
import org.fusesource.fabric.fab.VersionedDependencyId;

import java.util.HashSet;
import java.util.List;

@Command(name = "search", scope = "fab", description = "Search for all the available modules")
public class SearchCommand extends FabCommand {

    @Argument(index = 0, required = false, description = "Name of the module to list")
    private String name;

    @Override
    protected Object doExecute() throws Exception {
        OsgiModuleRegistry registry = Activator.registry;
        List<ModuleRegistry.Module> modules = registry.getApplicationModules();


        Table table = new Table("{1} | {2} | {3}", -20, -10, -40);
        table.add("Name", "Version", "Description");
        for (ModuleRegistry.Module module : modules) {
            ModuleRegistry.VersionedModule latest = module.latest();
            if( name==null || module.getName().indexOf(name) >=0 ) {
                table.add(module.getName(), latest.getId().getVersion(), latest.getDescription());
            }
        }
        table.print(session.getConsole());

        return null;
    }

    public static void main(String []args) {
        Table table = new Table("{1} | {2} | {3}", -20, -10, -40);
        table.add("Name", "Version", "Description");
        table.add("x", "y", "z");
        table.add("x", "y", "z");
        table.print(System.out);
    }
}
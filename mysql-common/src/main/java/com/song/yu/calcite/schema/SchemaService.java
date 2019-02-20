package com.song.yu.calcite.schema;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 下午5:11
 */

import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.tools.Frameworks;

import java.util.HashSet;
import java.util.Set;

public class SchemaService {

	private SchemaPlus rootSchema = Frameworks.createRootSchema(true);
	private Set<String> schemas = new HashSet<String>();
	private static final SchemaService INSTANCE = new SchemaService();

	public SchemaPlus getRootSchema() {
		return rootSchema;
	}

	private void addSchema(String schema) {
		MysqlSchemaPlus schemaPlus = new MysqlSchemaPlus();
		schemaPlus.setName(schema);
		schemaPlus.setParent(rootSchema);
		rootSchema.add(schema, schemaPlus);
	}

	private void dropSchema(String schema) {
		//todo, how to implement this function
		//TranslatableTable//AbstractQueryableTable

		//change kepler
		//todo
	}




}

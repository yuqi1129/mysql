package com.song.yu.calcite.schema;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 下午5:23
 */

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.apache.calcite.linq4j.tree.Expression;
import org.apache.calcite.materialize.Lattice;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.Function;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.SchemaVersion;
import org.apache.calcite.schema.Table;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MysqlSchemaPlus implements SchemaPlus {

	private String name;
	private SchemaPlus parent;
	Map<String, SchemaPlus> subSchema = Maps.newHashMap();
	Map<String, Function> functions = Maps.newHashMap();
	Map<String, Table> tableMap = Maps.newHashMap();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SchemaPlus getParent() {
		return parent;
	}

	public void setParent(SchemaPlus parent) {
		this.parent = parent;
	}

	public Table getTable(String name) {
		return tableMap.get(name);
	}

	public Set<String> getTableNames() {
		return tableMap.keySet();
	}

	public RelProtoDataType getType(String name) {
		return null;
	}

	public Set<String> getTypeNames() {
		return null;
	}

	public Collection<Function> getFunctions(String name) {
		return null;
	}

	public Set<String> getFunctionNames() {
		return functions.keySet();
	}

	public SchemaPlus getSubSchema(String name) {
		return subSchema.get(name);
	}

	public Set<String> getSubSchemaNames() {
		return subSchema.keySet();
	}

	public Expression getExpression(SchemaPlus parentSchema, String name) {
		return null;
	}

	public boolean isMutable() {
		return false;
	}

	public Schema snapshot(SchemaVersion version) {
		return null;
	}
	// ---------------------------------------------------------------------------
	public SchemaPlus getParentSchema() {
		return parent;
	}

	public SchemaPlus add(String name, Schema schema) {
		return null;
	}

	public void add(String name, Table table) {

	}

	public void add(String name, Function function) {

	}

	public void add(String name, RelProtoDataType type) {

	}

	public void add(String name, Lattice lattice) {

	}

	public <T> T unwrap(Class<T> clazz) {
		return null;
	}

	public void setPath(ImmutableList<ImmutableList<String>> path) {

	}

	public void setCacheEnabled(boolean cache) {

	}

	public boolean isCacheEnabled() {
		return false;
	}
}
